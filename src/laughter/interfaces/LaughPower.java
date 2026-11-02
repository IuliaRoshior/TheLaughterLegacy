package laughter.interfaces;

import laughter.types.Laugh;

public interface LaughPower {
    // возвращает, сколько морали снято у цели
    int activate(Laugh target);
}
