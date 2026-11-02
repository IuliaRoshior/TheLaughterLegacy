package laughter.interfaces;

import laughter.types.Laugh;

public interface Transmittable {
    // возвращает, сколько морали передано цели
    int transmitTo(Laugh target);
}
