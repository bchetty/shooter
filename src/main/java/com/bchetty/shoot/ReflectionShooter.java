package com.bchetty.shoot;

import com.bchetty.annotations.Shoot;
import com.bchetty.module.Module;
import java.lang.reflect.Constructor;

/**
 *
 * @author Babji Prashanth, Chetty
 */
public class ReflectionShooter implements Shooter {
    private Module module;

    public ReflectionShooter(Module module) {
        this.module = module;
    }

    @Override
    public Object shoot(Class klass) throws Exception {
        if (klass != null) {
            boolean flag = true;
            int index = 0;

            for (Constructor constructor : klass.getConstructors()) {
                if (constructor.isAnnotationPresent(Shoot.class)) {
                    if (flag && index == 0) { //To restrict to only one constructor injection
                        flag = false;
                        index++;

                        Class[] parameterTypes = constructor.getParameterTypes();
                        Object[] objArr = new Object[parameterTypes.length];

                        int i = 0;

                        for (Class c : parameterTypes) {
                            Class dependency = module.getMapping(c);

                            if (c.isAssignableFrom(dependency)) {
                                objArr[i++] = dependency.getConstructor().newInstance();
                            }
                        }

                        Object resObj = klass.getConstructor(parameterTypes).newInstance(objArr);

                        return resObj;
                    }
                }
            }
        }

        return null;
    }
    
}
