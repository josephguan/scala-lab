# conway
A simple implementation of conway's game of life. 

### Background
The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.

The "game" is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. One interacts with the Game of Life by creating an initial configuration and observing how it evolves or, for advanced players, by creating patterns with particular properties.


### Rules
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent. At each step in time, the following transitions occur:

1. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overcrowding.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.

### Examples

1.  Initial data (1 for alive, 0 for dead)

	|1|1|1|
	|---|---|---|
	|1|0|1|
	|0|1|1|

2. First round using the rules, it becomes:

	|1|0|1|
	|---|---|---|
	|1|0|0|
	|0|1|1|

3. Second round using the rules, it becomes:

	|0|1|0|
	|---|---|---|
	|1|0|1|
	|0|1|0|

4. Using the rules again, it will not change any more. (stable)
