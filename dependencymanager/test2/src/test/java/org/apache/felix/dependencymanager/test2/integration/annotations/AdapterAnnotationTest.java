/*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.apache.felix.dependencymanager.test2.integration.annotations;

import org.apache.felix.dependencymanager.test2.components.Ensure;
import org.apache.felix.dependencymanager.test2.integration.common.TestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.junit.PaxExam;
import org.osgi.framework.ServiceRegistration;

/**
 * Use case: Verify Aspect Annotations usage.
 */
@RunWith(PaxExam.class)
public class AdapterAnnotationTest extends TestBase {
    /**
     * Check if an adapter gets injected with its adaptee using default auto config mode.
     * @throws Throwable 
     */
    @Test
    public void testAnnotatedAdapterAutoConfig() throws Throwable {
        Ensure e = new Ensure();
        ServiceRegistration er = register(e, "adapter.autoConfig");
        e.waitForStep(3, 10000);
        e.ensure();
        er.unregister();
    }

    /**
     * Check if an adapter gets injected with its adaptee in a named class field.
     */
    @Test
    public void testAnnotatedAdapterAutoConfigField() throws Throwable {
        Ensure e = new Ensure();
        ServiceRegistration er = register(e, "adapter.autoConfig.field");
        e.waitForStep(3, 10000);
        e.ensure();
        er.unregister();
    }

    /**
     * Check if an adapter gets injected with its adaptee in a callback method.
     */
    @Test
    public void testAnnotatedAdapterCallback() {
        Ensure e = new Ensure();
        ServiceRegistration er = register(e, "adapter.callback");
        e.waitForStep(2, 10000);
        er.unregister();
        e.waitForStep(4, 10000);
    }
}