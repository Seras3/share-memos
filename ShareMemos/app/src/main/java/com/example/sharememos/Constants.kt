package com.example.sharememos

import android.Manifest

object Constants {

    const val STORAGE_FOLDER_NAME = "ShareMemos"
    const val TAG = "SHARE-MEMOS"
    const val FILE_PRE_TAG = "SM-"
    const val FILE_NAME_FORMAT = "yy-MM-dd-HH-mm-ss-SSSS"
    const val REQUEST_CODE_PERMISSION = 123
    const val REQUEST_IMAGE_CAPTURE = 1
    val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
}