package com.shashank.dagger2cwm.di.auth

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope


@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class AuthScope()
