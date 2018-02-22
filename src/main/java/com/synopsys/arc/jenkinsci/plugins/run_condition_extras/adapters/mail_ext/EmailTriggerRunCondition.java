/*
 * The MIT License
 *
 * Copyright (c) 2016 Oleg Nenashev.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.synopsys.arc.jenkinsci.plugins.run_condition_extras.adapters.mail_ext;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.plugins.emailext.plugins.EmailTrigger;
import java.util.logging.Level;
import javax.annotation.CheckForNull;
import org.jenkins_ci.plugins.run_condition.RunCondition;
import java.util.logging.Logger;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 *
 * @author Oleg Nenashev
 */
public class EmailTriggerRunCondition extends RunCondition {
 
    @CheckForNull
    private EmailTrigger trigger;

    private static final Logger LOGGER = Logger.getLogger(EmailTriggerRunCondition.class.getName());
    
    @DataBoundConstructor
    public EmailTriggerRunCondition(EmailTrigger trigger) {
        this.trigger = trigger;
    }

    public EmailTrigger getTrigger() {
        return trigger;
    }
    
    @Override
    public boolean runPerform(AbstractBuild<?, ?> ab, BuildListener bl) throws Exception {
        if (trigger == null) {
            LOGGER.log(Level.INFO, "foo");
            return false;
        }
        return trigger.trigger(ab, bl);
    }

    @Override
    public boolean runPrebuild(AbstractBuild<?, ?> ab, BuildListener bl) throws Exception {
        if (trigger == null) {
            LOGGER.log(Level.INFO, "foo");
            return false;
        }
        return trigger.trigger(ab, bl);
    }
    
    @Extension(optional = true)
    public static class AlwaysRunDescriptor extends RunConditionDescriptor {

        @Override
        public String getDisplayName() {
            return "Email Trigger Condition";
        }
    }
    
}
