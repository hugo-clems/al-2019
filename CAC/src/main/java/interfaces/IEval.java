package interfaces;


import domaine.Configuration;
import enumeration.Appreciation;

import java.util.List;

public interface IEval {

    void noterConfiguration(int configuration, Appreciation appreciation);

    List<Configuration> getConfigurations();
}
