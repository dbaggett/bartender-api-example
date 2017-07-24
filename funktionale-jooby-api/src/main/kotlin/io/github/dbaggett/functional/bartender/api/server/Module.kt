package io.github.dbaggett.functional.bartender.api.server

import com.google.inject.AbstractModule
import com.google.inject.Binder
import com.typesafe.config.Config
import org.jooby.Env
import org.jooby.Jooby

abstract class Module : Jooby.Module, AbstractModule() {
    override fun configure(env: Env, conf: Config, binder: Binder) {
        binder.install(this)
    }

    override public fun binder(): Binder {
        return super.binder()
    }

    override fun configure() {
    }
}