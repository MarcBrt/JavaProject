package fr.projet.diagnostic;

import fr.projet.diagnostic.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ErrorGeneratorCore {

    private List<Cas> cass = new ArrayList<>();
    private List<Cas> errorCass = new ArrayList<>();
    private int cptCasError = 0;

    /**
     * Constructeur de la classe ErrorGeneratorCore
     * @param normalCases Liste contenant tous les cas normaux
     */
    public ErrorGeneratorCore(List<Cas> normalCases) {
        this.cass = normalCases;
    }

    /**
     * Méthode permettant de générer une liste de cas défaillant en récupérant et générant
     *  des erreurs via des cas normaux
     * @return Retourne une liste de Cas totalement défaillant
     */
    public List<Cas> generateError() {
        for( Cas cas : cass ) {
            generateErrorForEachCase(cas);
        }

        return this.errorCass;
    }

    /**
     * Méthode qui permet de générer des erreur sur le triplet séléctionné via au cas donné en paramètre
     * @param cas Cas où l'on doit générer l'erreur
     */
    private void generateErrorForEachCase( Cas cas ) {
        int nbTriplet = cas.tripletCount();
        Failing state = new Failing(1);

        if( nbTriplet != 1 ) {
            for(int i = 1; i < nbTriplet; i++) {
                List<Triplet> triplets = new ArrayList<>();
                for(int indexTriplet = 0; indexTriplet < nbTriplet; indexTriplet++) {
                    if( indexTriplet != i ){
                        triplets.add( cas.p.get(indexTriplet) );
                    }
                    else {
                        triplets.add(generateTripletError(cas.p.get(indexTriplet)));
                    }
                }
                state.description = "=> Absence de " + cas.getP().get(i).ec;
                Cas errorCase = new Cas(cas.id, triplets, state);

                cptCasError++;

                this.errorCass.add( errorCase );
            }
        }
    }

    /**
     *  Méthode permettant de générer une erreur sur un triplet donné
     * @param triplet triplet où l'erreur doit être générer
     * @return triplet contenant l'erreur
     */
    private Triplet generateTripletError(Triplet triplet) {
        Intervalle intervalleCase = triplet.ct;
        Intervalle errorIntervale = new Intervalle( 0, intervalleCase.bs, intervalleCase.bs);

        return new Triplet(triplet.idTriplet, triplet.er, "S_"+triplet.ec, errorIntervale);
    }
}
