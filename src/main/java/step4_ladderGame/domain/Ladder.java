package step4_ladderGame.domain;

import step4_ladderGame.dto.LadderDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Ladder {

    private final List<LadderLine> ladderLines;

    private Ladder(List<LadderLine> ladderLines) {
        this.ladderLines = ladderLines;
    }

    public static Ladder of(int height, int playerCount, RandomPointStrategy createPointStrategy) {
        return Stream.generate(() -> LadderLine.of(playerCount, createPointStrategy))
                .limit(height)
                .collect(Collectors.collectingAndThen(toList(), Ladder::new));
    }

    public static Ladder of(List<LadderLine> ladderLines) {
        return new Ladder(ladderLines);
    }

    public int size() {
        return ladderLines.size();
    }


    public Player move(Player player) {
        final Player[] movePlayer = {player};
        return ladderLines.stream()
                .map(line -> movePlayer[0] = line.move(movePlayer[0]))
                .skip(ladderLines.size() - 1)
                .collect(toList()).get(0);
    }

    public LadderDto toDto() {
        return ladderLines.stream()
                .map(LadderLine::toDto)
                .collect(collectingAndThen(toList(), LadderDto::new));
    }
}