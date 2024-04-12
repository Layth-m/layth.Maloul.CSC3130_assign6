
import java.util.LinkedList;
public class ElectionSystem {
    private Election election;
    private int numberOfElectorateVotes;

    public ElectionSystem(LinkedList<String> candidates, int numberOfElectorateVotes) {
        this.election = new Election();
        this.election.initializeCandidates(candidates);
        this.numberOfElectorateVotes = numberOfElectorateVotes;
    }

    public void simulateVoting() {
        for (int i = 0; i < numberOfElectorateVotes; i++) {
            election.castRandomVote();
        }
    }

    public void rigElectionFor(String candidate) {
        election.rigElection(candidate, numberOfElectorateVotes);
    }
    public void displayTopKCandidates(int k) {
        System.out.println("Top " + k + " candidates:");
        for (String candidate : election.getTopKCandidates(k)) {
            System.out.println(candidate);
        }
    }
    public void audit() {
        election.auditElection();
    }

      public static void main(String[] args) {
        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");

        ElectionSystem system = new ElectionSystem(candidates, 5);
        system.election.castVote("Cole Train");
        system.election.castVote("Cole Train");
        system.election.castVote("Marcus Fenix");
        system.election.castVote("Anya Stroud");
        system.election.castVote("Anya Stroud");
        // Simulate the voting process
        //system.simulateVoting();

        // Rig the election for the spy mission
        system.rigElectionFor("Anya Stroud");

        system.displayTopKCandidates(3);

        system.audit();
    }
}


