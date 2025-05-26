class Pair implements Comparable<Pair> {
    int node;
    int weight;

    public Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(this.weight, other.weight); // Ascending order by weight
    }
}