package interfaces;


import domaine.Configuration;

import java.util.List;

public interface IEval {

    void noterConfiguration(int configuration, Appreciation appreciation);

    List<Configuration> Configurations();
}
