package org.example.iec61850datatypes.measurements;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class HWYE {
    private List<CMV> phsAH = new ArrayList<>();

    private List<CMV> phsBH = new ArrayList<>();

    private List<CMV> phsCH = new ArrayList<>();

    private List<CMV> neutH = new ArrayList<>();
    private final  int numHar = 5;
    public HWYE() {
        setLists();
    }

    private void setLists() {
        for(int i = 0; i < numHar; i++){
            phsAH.add(new CMV());
            phsBH.add(new CMV());
            phsCH.add(new CMV());
            neutH.add(new CMV());
        }
    }
}
