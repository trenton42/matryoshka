# Matryoshka - the doll smuggler

A doll smuggling program

## Installation

Building the project requires [leiningen](https://github.com/technomancy/leiningen)

## Usage

Once leiningen is installed, test can be run by calling

    $ lein test

Matryoshka accepts a table of values on standard in and prints the results on standard out. The input format is as follows (note that the first line is a header):

```
name    weight value
luke        9   150
anthony    13    35
candice   153   200
dorothy    50   160
```

The maximum weight is specified by the first command line argument. With the table of dolls in a file called `test.txt` and a maximum weight of 400, the program would be called by running:

    $ cat test.txt | lein run 400

## Caveats

Very large weight values or very long lists will cause the program to take a very long time. A future version could utilize an approximation algorithm to accommodate shifting a large number of dolls when time is of the essence.

## Acknowledgements

The packing algorithm was based on the [Wikipedia article](https://en.wikipedia.org/wiki/Knapsack_problem#0/1_knapsack_problem) describing one solution of the knapsack problem. Additional test cases were derived from [the datasets](https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html) provided by John Burkardt.