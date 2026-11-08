package com.ashmeet.hyperlauncher.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.ashmeet.hyperbackend.instances.Instances
import com.ashmeet.hyperlauncher.theme.PojavTheme
import com.ashmeet.hyperlauncher.utils.HyperUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.zip.ZipInputStream

class RabbitBridgeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (HyperUtils.checkStorageInteractive(this)) HyperUtils.initStorageConstants(applicationContext)
        setContent {
            PojavTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Text("Rabbit Bridge", style = MaterialTheme.typography.headlineMedium)
                        Text("Import a ZIP containing controls/, mods/ and/or config/ folders for the selected Minecraft instance.")
                        Text("Compatible control JSON, JAR mods and config files are supported. Existing files are skipped.")
                        Button(onClick = { pickZip() }) { Text("Choose profile ZIP") }
                    }
                }
            }
        }
    }

    private fun pickZip() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/zip"
            putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("application/zip", "application/x-zip-compressed", "application/octet-stream"))
        }
        startActivityForResult(intent, 4107)
    }

    @Deprecated("Legacy activity result")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != 4107 || resultCode != RESULT_OK) return
        val uri = data?.data ?: return
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) { importZip(uri) }
            Toast.makeText(this@RabbitBridgeActivity, result, Toast.LENGTH_LONG).show()
            if (result.startsWith("Imported")) finish()
        }
    }

    private fun importZip(uri: Uri): String {
        val instance = try { Instances.loadSelectedInstance() } catch (_: Exception) { null }
            ?: return "Select a Minecraft instance first."
        val controlDir = File(HyperUtils.CTRLMAP_PATH ?: return "Control storage unavailable.")
        val gameDir = instance.gameDirectory
        var controls = 0; var mods = 0; var configs = 0; var total = 0L; var entries = 0
        try {
            contentResolver.openInputStream(uri)?.use { input ->
                ZipInputStream(input).use { zip ->
                    while (true) {
                        val entry = zip.nextEntry ?: break
                        if (++entries > 2000) return "Archive has too many entries."
                        val name = entry.name.replace('\\', '/')
                        val parts = name.split('/')
                        if (entry.isDirectory || name.startsWith('/') || parts.any { it == ".." || it.isBlank() }) { zip.closeEntry(); continue }
                        val root = parts.first()
                        if (root !in setOf("controls", "mods", "config") || parts.size < 2) { zip.closeEntry(); continue }
                        val fileName = parts.last()
                        if (root == "controls" && !fileName.endsWith(".json", true)) { zip.closeEntry(); continue }
                        if (root == "mods" && !fileName.endsWith(".jar", true)) { zip.closeEntry(); continue }
                        val base = when (root) {
                            "controls" -> controlDir
                            "mods" -> File(gameDir, "mods")
                            else -> File(gameDir, "config")
                        }
                        val dest = File(base, parts.drop(1).joinToString("/")).canonicalFile
                        if (!dest.toPath().startsWith(base.canonicalFile.toPath()) || dest.exists()) { zip.closeEntry(); continue }
                        dest.parentFile?.mkdirs()
                        var size = 0L
                        dest.outputStream().buffered().use { out ->
                            val buffer = ByteArray(8192)
                            while (true) {
                                val n = zip.read(buffer); if (n < 0) break
                                size += n; total += n
                                if (size > 100L*1024*1024 || total > 500L*1024*1024) { dest.delete(); return "Archive exceeds import size limit." }
                                out.write(buffer, 0, n)
                            }
                        }
                        when(root) { "controls" -> controls++; "mods" -> mods++; "config" -> configs++ }
                        zip.closeEntry()
                    }
                }
            } ?: return "Could not open ZIP."
        } catch (e: Exception) { return "Import failed: ${e.localizedMessage ?: "invalid ZIP"}" }
        if (controls + mods + configs == 0) return "No supported files found in ZIP."
        return "Imported $controls controls, $mods mods and $configs config files."
    }
}
