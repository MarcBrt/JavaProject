package fr.projet.diagnostic;

import fr.projet.diagnostic.entity.Cas;
import fr.projet.diagnostic.entity.Triplet;

import java.util.ArrayList;
import java.util.List;

public class SimilarityCore {

    public List<Float> tableau = new ArrayList<>();

    public SimilarityCore(ArrayList<Cas> similarityCase, Cas newCase) {
        for(Cas testedCase: similarityCase) {
            if ( testedCase.tripletCount() == newCase.tripletCount() ) {
                tableau.add( distance(testedCase.p, newCase.p) );
            }
            else {
                throw new Error("Size not equal");
            }
        }
    }

    private float distance(ArrayList<Triplet> simiTriplets, ArrayList<Triplet> newCaseTriplets) {
        float sum = 0;
        for(int i = 0; i < newCaseTriplets.size(); i++) {
            sum += distanceTriplet( simiTriplets.get(i), newCaseTriplets.get(i));
        }

        return sum/6;
    }

    private float distanceTriplet(Triplet simiTriplet, Triplet newCaseTriplet) {
        return distanceEvenementEr( simiTriplet.er, newCaseTriplet.er) +
                distanceEvenementEc( simiTriplet.ec, newCaseTriplet.ec) +
                ( calculIpos(simiTriplet.ct, newCaseTriplet.ct) / 100 );
    }

    private short distanceEvenementEr(String simiER, String newCaseER) {
        if( simiER.contentEquals(newCaseER) ) {
            return 0;
        }
        return 1;
    }

    private short distanceEvenementEc(String simiEC, String newCaseEC) {
        if( simiEC.contentEquals(newCaseEC) ) {
            return 0;
        }
        return 1;
    }
}
