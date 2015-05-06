package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class NullPointerMaker implements BadThing {
    public void generateNullPointer() {
        new Person().getName().toString();
    }

    public String badThingDescription() {
        return "Generates a typical null pointer error.";
    }

    @Override
    public String badThingId() {
        return "generate-null-pointer-exception";
    }

    public String badThingName() {
        return "Generate NullPointerException";
    }

    public String doBadThing() throws Exception {
        new NullPointerMaker().generateNullPointer();
        return null;
    }

    class Person {
        String getName() {
            return null;
        }
    }
}