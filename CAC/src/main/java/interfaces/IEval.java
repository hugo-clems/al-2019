package interfaces;


import domaine.Configuration;
import enumeration.Appreciation;

import java.util.Set;

public interface IEval {

    void noterConfiguration(int configuration, Appreciation appreciation);

    Set<Configuration> getConfigurations();
}
