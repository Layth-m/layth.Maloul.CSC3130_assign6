import java.util.*;
public class Election {
    private PriorityQueue<Candidate> maxHeap;
    private HashMap<String, Integer> voteCounts;
    private Random random;

    private class Candidate implements Comparable<Candidate> {
        String name;
        int votes;

        Candidate(String name) {
            this.name = name;
            this.votes = 0;
        }

        @Override
        public int compareTo(Candidate other) {
            return Integer.compare(other.votes, this.votes);
        }
    }

    public Election() {
        this.maxHeap = new PriorityQueue<>();
        this.voteCounts = new HashMap<>();
        this.random = new Random();
    }

    public void initializeCandidates(LinkedList<String> candidates) {
        for (String candidateName : candidates) {
            Candidate candidate = new Candidate(candidateName);
            maxHeap.offer(candidate);
            voteCounts.put(candidateName, 0);
        }
    }

    public void castVote(String candidateName) {
        if (!voteCounts.containsKey(candidateName)) {
            System.out.println("Candidate does not exist.");
            return;
        }

        int currentVotes = voteCounts.get(candidateName);
        voteCounts.put(candidateName, currentVotes + 1);
        refreshHeap();
    }

    public void castRandomVote() {
        List<String> keys = new ArrayList<>(voteCounts.keySet());
        String randomCandidate = keys.get(random.nextInt(keys.size()));
        castVote(randomCandidate);
    }

    public void rigElection(String candidate, int p) {
        if (!voteCounts.containsKey(candidate)) {
            System.out.println("Candidate does not exist.");
            return;
        }

        voteCounts.put(candidate, voteCounts.get(candidate) + p);
        refreshHeap();
    }

    public List<String> getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        Iterator<Candidate> iterator = maxHeap.iterator();

        while (iterator.hasNext() && k > 0) {
            topCandidates.add(iterator.next().name);
            k--;
        }

        return topCandidates;
    }

    public void auditElection() {
        PriorityQueue<Candidate> copy = new PriorityQueue<>(maxHeap);

        while (!copy.isEmpty()) {
            Candidate candidate = copy.poll();
            System.out.println(candidate.name + ": " + candidate.votes);
        }
    }

    private void refreshHeap() {
       // maxHeap.clear();
        PriorityQueue<Candidate> newHeap = new PriorityQueue<>();

        // Re-insert candidates into the new heap with updated vote counts
        for (Candidate candidate : maxHeap) {
            candidate.votes = voteCounts.get(candidate.name);
            newHeap.offer(candidate);
        }

        maxHeap = newHeap;

    }
}