package com.example.taohook

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class Main : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        lpparam?.apply {
            XposedHelpers.findAndHookMethod(
                    "mtopsdk.mtop.global.SwitchConfig",
                    classLoader,
                    "isGlobalSpdySwitchOpen",
                    MainHook()
            )

        }
    }

    inner class MainHook : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam?) {
            super.afterHookedMethod(param)
            XposedBridge.log("运行到我啦")
            param?.apply {
                result = false
            }
        }
    }
}