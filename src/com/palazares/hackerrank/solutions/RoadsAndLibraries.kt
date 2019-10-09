package com.palazares.hackerrank.solutions

fun roadsAndLibraries(n: Int, c_lib: Int, c_road: Int, cities: Array<Array<Int>>): Long {
    if (c_lib <= c_road || cities.isEmpty()) return n * c_lib.toLong()

    var i = 1;
    val cities2Clusters = Array(n) { i++ }
    val clusters2Cities = cities2Clusters.associate { Pair(it, hashSetOf(it)) }.toMutableMap()

    for (road in cities) {
        if (cities2Clusters[road[0] - 1] != cities2Clusters[road[1] - 1]) {
            val newCluster = cities2Clusters[road[0] - 1]
            val oldCluster = cities2Clusters[road[1] - 1]
            val oldClusterSet = clusters2Cities[oldCluster]
            val newClusterSet = clusters2Cities[newCluster]
            oldClusterSet!!.forEach { cities2Clusters[it - 1] = newCluster }
            clusters2Cities.remove(oldCluster)
            clusters2Cities[newCluster] = newClusterSet!!.union(oldClusterSet).toHashSet()
        }
    }
    val clustersCount = clusters2Cities.size


    return (n - clustersCount) * c_road + clustersCount * c_lib.toLong();
}

fun main() {
    println(roadsAndLibraries(3, 2, 1, arrayOf(arrayOf(1, 2), arrayOf(3, 1), arrayOf(2, 3))))
    println(roadsAndLibraries(6, 2, 5, arrayOf(
            arrayOf(1, 3), arrayOf(3, 4), arrayOf(2, 4),
            arrayOf(1, 2), arrayOf(2, 3), arrayOf(5, 6))))
}