package com.dev9.crash.bad;

import com.dev9.crash.BadThing;
import org.springframework.stereotype.Service;

@Service
public class SimplySystemExit implements BadThing {
    public String getBadThingDescription() {
        return "Try to shut down the server with System.exit().  This completely skips the normal server termination and can leave the server in an inconsistent state.";
    }

    @Override
    public String getBadThingId() {
        return "system-exit";
    }

    public String getBadThingName() {
        return "Simply System Exit";
    }

    public String doBadThing() throws Exception {
        System.exit(-1);
        return null;
    }
}
