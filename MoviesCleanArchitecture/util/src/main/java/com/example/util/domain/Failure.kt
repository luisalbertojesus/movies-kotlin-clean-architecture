package com.example.util.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class Failure : Parcelable {
    @Parcelize
    object NetworkConnection : Failure()

    @Parcelize
    object Http : Failure()

    @Parcelize
    object UnExpected : Failure()
}