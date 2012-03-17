package com.bchetty.shoot;

import com.bchetty.annotations.Shoot;
import java.lang.reflect.Constructor;

import com.bchetty.module.Module;

public class ShooterDI {
    public static Shooter getShooter(Module module) {
        module.configure();
        return new ReflectionShooter(module);
    }
}
