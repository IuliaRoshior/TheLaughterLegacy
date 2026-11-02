package laughter.interfaces;

import laughter.types.Laugh;
// Смех, который умеет использовать накопленное Эхо арены.
public interface SoulResonance {
    // возвращает, сколько Эха потрачено
    int resonate(Laugh target, int echo);
}
