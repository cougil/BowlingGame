package com.codurance;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @Before
    public void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    public void no_strike_no_spare_in_turns_get_valid_score() {
        String game = "9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(90);
    }

    @Test
    public void all_turns_without_strikes_and_spares() {
        String game = "52|52|52|52|52|52|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(70);
    }

    @Test
    public void one_spare_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|5/|52|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(78);
    }

    @Test
    public void one_strike_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|X|52|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(80);
    }

    @Test
    public void two_consecutive_strikes_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|X|X|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(56+20+10+5+7);
    }

    @Test
    public void one_strikes_followed_by_miss_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|X|5-|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(56+10+5+5);
    }

    @Test
    public void one_strikes_followed_by_spare_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|X|5/|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(56+10+10+10+5);
    }

    @Test
    public void one_spare_followed_by_strike_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|5/|X|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(56+10+10+10+7);
    }

    @Test
    public void one_spare_followed_by_miss_in_the_game_not_in_the_last_turn() {
        String game = "52|52|52|52|5/|-4|52|52|52|52||";
        assertThat(bowlingGame.getScore(game)).isEqualTo(56+10+4);
    }

    @Test
    public void all_spares_means_bonus() {
        String game = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5";
        assertThat(bowlingGame.getScore(game)).isEqualTo(150);
    }

//    @Test
//    public void all_squares_with_bonus() {
//        String game = "X|X|X|X|X|X|X|X|X|X||XX";
//        assertThat(bowlingGame.getScore(game)).isEqualTo(300);
//    }


    /*
    X     |7/  |9-|X   |-8|8/  |-6|X       |X      |X     ||81 = 167

    10+7+3|10+9|9 |10+8|8 |10+0|6 |10+10+10|10+10+8|10+8+1||9  = 186
    20    |29  |9 |18  |8 |10  |6 |30      |28     |19    ||9  = 186

    10+7+3|10+9|9 |10+8|8 |10+0|6 |10+10+10|10+10+8|10+8+1||9  = 177
    20    |29  |9 |18  |8 |10  |6 |30      |28     |19    ||0  = 177


    https://www.thoughtco.com/bowling-scoring-420895
    X	    |7/     |	7 2|	9/  |	X	    |X	    |X	    |2 3	|6/     |	7/  | 3  = 168
    10+10   |10+7   |9     |10+10   |10+10+10   |10+10+2|10+5   |5      |10+7   |10  +3 | ---  <<<< 171
     */
}