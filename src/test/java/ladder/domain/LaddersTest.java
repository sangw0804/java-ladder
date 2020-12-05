package ladder.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LaddersTest {

    private Line line;

    @BeforeEach
    public void init(){

        List<Point> lineConnections = new ArrayList<>();
        lineConnections.add(new Point(true));
        lineConnections.add(new Point(false));

        line = new Line(lineConnections);
    }

    @DisplayName("Ladders 생성 테스트")
    @Test
    void laddersConstructorTest(){
        // given
        String[] playNames = {"AAA", "BBB", "CCC"};

        // when
        Ladder ladder1 = new Ladder(line);
        Ladder ladder2 = new Ladder(line);

        List<Ladder> ladderList = new ArrayList<>();
        ladderList.add(ladder1);
        ladderList.add(ladder2);
        Ladders ladders = new Ladders(ladderList);

        // then
        assertThat(ladders.getLadders()).containsExactly(new Ladder(line), new Ladder(line));
    }
}