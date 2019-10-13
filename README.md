# Percolation

Here, modelling a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. The system percolates if there is a full site in the bottom row. In other words, a system percolates if one fills all open sites connected to the top row and that process fills some open site on the bottom row

In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. When n is sufficiently large, there is a threshold value p* such that when p < p* a random n-by-n grid almost never percolates, and when p > p*, a random n-by-n grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived.

To estimate the percolation Threshold, I used **Monte Carlo Simulation** . By repeating the computation experiment and averaging the results, obtained is a more accurate estimate of the percolation threshold. 
