package Game;

import java.util.Random;
import java.util.Set;

public class Controller {
    int size;
    int[] elements;
    Random random = new Random();
    public boolean GameOver = false;

    Controller(int size) {
        assert size > 0;
        this.size = size;
        elements = new int[size * size];
        reset();
    }

    enum Direction {
        Up,
        Down,
        Left,
        Right
    }

    int[] update(Direction dir) {
        switch (dir) {
            case Up -> slideUp();
            case Down -> slideDown();
            case Left -> slideLeft();
            case Right -> slideRight();
        }
        GameOver = !spawnNewCell();
        return elements;
    }

    void reset() {
        for (int i = 0; i < elements.length; ++i)
            elements[i] = 0;
        for (int i = 0; i < 3; ++i)
            spawnNewCell();
        GameOver=false;
    }

    int getScore(){
        int val=0;
        for(int i:elements)
            val+=i;
        return val;
    }

    int[] getElements() {
        return elements;
    }

    private boolean spawnNewCell() {
        boolean hasEmpty = false;
        for (var i : elements) {
            if (i == 0) {
                hasEmpty = true;
                break;
            }
        }
        if (!hasEmpty) return false;
        int i = random.nextInt(0, size * size);
        while (elements[i] != 0) {
            i = random.nextInt(0, size * size);
        }
        elements[i] = 2;
        return true;
    }

    private int At(int x, int y) {
        return elements[x + size * y];
    }

    private void SetCellVal(int x, int y, int val) {
        elements[x + size * y] = val;
    }

    private void slideLeft() {
        for (int x = 1; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                if (At(x, y) == 0) continue;
                int lastEmpty = x;
                for (int i = x - 1; i >= 0; --i) {
                    if (At(i, y) != 0) {
                        break;
                    }
                    lastEmpty = i;
                }
                if (lastEmpty != 0)
                    if (At(lastEmpty - 1, y) == At(x, y)) {
                        SetCellVal(lastEmpty - 1, y, At(x, y) * 2);
                        SetCellVal(x, y, 0);
                        continue;
                    }
                if (lastEmpty != x) {
                    SetCellVal(lastEmpty, y, At(x, y));
                    SetCellVal(x, y, 0);
                }
            }
        }
    }

    private void slideRight() {
        for (int x = size - 2; x >= 0; --x) {
            for (int y = 0; y < size; ++y) {
                if (At(x, y) == 0) continue;
                int lastEmpty = x;
                for (int i = x + 1; i < size; ++i) {
                    if (At(i, y) != 0) {
                        break;
                    }
                    lastEmpty = i;
                }
                if (lastEmpty < size - 1)
                    if (At(lastEmpty + 1, y) == At(x, y)) {
                        SetCellVal(lastEmpty + 1, y, At(x, y) * 2);
                        SetCellVal(x, y, 0);
                        continue;
                    }
                if (lastEmpty != x) {
                    SetCellVal(lastEmpty, y, At(x, y));
                    SetCellVal(x, y, 0);
                }
            }
        }
    }

    private void slideUp() {
        for (int y = 1; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                if (At(x, y) == 0) continue;
                int lastEmpty = y;
                for (int i = y - 1; i >= 0; --i) {
                    if (At(x, i) != 0) {
                        break;
                    }
                    lastEmpty = i;
                }
                if (lastEmpty != 0)
                    if (At(x, lastEmpty - 1) == At(x, y)) {
                        SetCellVal(x, lastEmpty - 1, At(x, y) * 2);
                        SetCellVal(x, y, 0);
                        continue;
                    }
                if (lastEmpty != y) {
                    SetCellVal(x, lastEmpty, At(x, y));
                    SetCellVal(x, y, 0);
                }
            }
        }
    }

    private void slideDown() {
        for (int y = size - 2; y >= 0; --y) {
            for (int x = 0; x < size; ++x) {
                if (At(x, y) == 0) continue;
                int lastEmpty = y;
                for (int i = y + 1; i < size; ++i) {
                    if (At(x, i) != 0) {
                        break;
                    }
                    lastEmpty = i;
                }
                if (lastEmpty < size - 1)
                    if (At(x, lastEmpty + 1) == At(x, y)) {
                        SetCellVal(x, lastEmpty + 1, At(x, y) * 2);
                        SetCellVal(x, y, 0);
                        continue;
                    }
                if (lastEmpty != y) {
                    SetCellVal(x, lastEmpty, At(x, y));
                    SetCellVal(x, y, 0);
                }
            }
        }
    }
}
