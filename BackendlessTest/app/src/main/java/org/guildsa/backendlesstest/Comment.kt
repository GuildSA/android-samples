package org.guildsa.backendlesstest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Comment(var objectId: String? = null,
              var message: String? = null,
              var authorEmail: String? = null) : Parcelable
