package assignment1;

import java.util.ArrayList;
import java.util.List;

import assignment1.Piece.Type;

public class Musketeer extends Piece {

    public Musketeer(MovementBehaviour movementBehaviour) {
        super("X", Type.MUSKETEER, movementBehaviour);
    }
}
