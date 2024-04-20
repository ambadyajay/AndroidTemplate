package com.example.androidprojecttemplate.core.networking.type_def

import arrow.core.Either
import com.example.androidprojecttemplate.core.networking.exceptions.NetworkExceptions
import com.example.androidprojecttemplate.core.networking.response.AndroidTemplateResponse

typealias RemoteResponse<T> = Either<NetworkExceptions, AndroidTemplateResponse<T>>