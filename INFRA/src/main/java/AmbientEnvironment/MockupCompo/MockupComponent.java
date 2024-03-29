
/*
 * Copyright (c) 2018.  Younes Walid, IRIT, University of Toulouse
 */

package AmbientEnvironment.MockupCompo;

import AmbientEnvironment.OCPlateforme.OCComponent;
import AmbientEnvironment.OCPlateforme.OCService;

import java.util.ArrayList;

public class MockupComponent extends OCComponent {
    protected String name;

    public MockupComponent(String name, ArrayList<OCService> providedservices, ArrayList<OCService> requiredServices) {
        this.name = name;
        this.providedServices = new ArrayList<>(providedservices);
        this.requiredServices = new ArrayList<>(requiredServices);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Component " + name + " Provides " + providedServices + " & Requires " + requiredServices + "]";

    }
}
