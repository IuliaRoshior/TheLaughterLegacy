# ☠️ The Laughter Legacy

### An Object-Oriented Java Project Inspired by One Piece

**The Laughter Legacy** is an educational Java project that explores Object-Oriented Programming through the world of *One Piece*.

The main idea is simple: every character has a unique laugh, represented as a Java object with its own properties, abilities, and influence on other characters.

Laughter becomes more than a sound. It can attack, intimidate, inspire, transfer emotions, and preserve memories.

The project combines Java OOP principles with a small interactive model inspired by the characters and atmosphere of One Piece.

## Project Concept

In the world of One Piece, laughter is part of a character's identity.

- Luffy: `SHISHISHI!`
- Kaido: `WORORORO!`
- Marshall D. Teach: `ZEHAHAHAHA!`
- Brook: `YOHOHOHO!`
- Nico Robin: `FUFUFU!`

Each type of laughter has different properties and abilities. Characters can interact with each other, changing their morale through attacks, fear, music, and inherited memories.

## OOP Concepts

The project demonstrates the following Java concepts:

- **Abstraction** – an abstract base class `Laugh`.
- **Inheritance** – a hierarchy of seven classes.
- **Encapsulation** – private fields, constructors, and getter methods.
- **Polymorphism** – different implementations of overridden methods.
- **Interfaces** – four interfaces defining character abilities.
- **Static members** – shared object counter and utility methods.
- **Final members** – constants, methods, and a final class.
- **Type casting** – working with objects through base classes and interfaces.
- **Instanceof** – checking object types at runtime.
- **Arrays of objects** – storing and processing different character types.

## Class Hierarchy

The project contains seven classes in its main inheritance hierarchy:

```text
                  Laugh (abstract)
                       |
          +------------+------------+
          |                         |
     BattleLaugh              ForcedSmileLaugh
          |
     +----+----+
     |         |
 DreadLaugh  MusicalLaugh
     |
 CruelLaugh (final)
```

## Interfaces

Four interfaces define different abilities:

| Interface | Method | Purpose |
|---|---|---|
| `LaughPower` | `activate()` | Attack using laughter |
| `Intimidating` | `frighten()` | Reduce an opponent's morale through fear |
| `Transmittable` | `transmitTo()` | Transfer morale to another character |
| `SoulResonance` | `resonate()` | Use accumulated Echo to affect another character |

Different classes implement these interfaces according to their own abilities.

## Character Classes

| Class | Character inspiration | Special ability |
|---|---|---|
| `Laugh` | General concept | Base properties and morale |
| `BattleLaugh` | Monkey D. Luffy | Combat and impact power |
| `DreadLaugh` | Kaido | Intimidation and fear |
| `CruelLaugh` | Marshall D. Teach | Mockery and cruelty |
| `MusicalLaugh` | Brook | Music and emotional resonance |
| `ForcedSmileLaugh` | Toko | Revealing true emotions |
| `InheritedLaugh` | Nico Robin | Inheriting laughter and memories |

## Main Features

- Random generation of character properties.
- Manual character data input through the keyboard.
- Individual character abilities and interactions.
- Morale management with values between -100 and 100.
- Sound wave generation and volume management.
- Inheritance of laughter from another character.
- Polymorphic method execution.
- Saving character information to a `laughs.txt` file.
- Object arrays and interface-based operations.

## Project Structure

```text
src/
└── laughter/
    ├── app/
    │   └── Main.java
    │
    ├── characters/
    │   ├── CruelLaugh.java
    │   ├── DreadLaugh.java
    │   └── MusicalLaugh.java
    │
    ├── interfaces/
    │   ├── Intimidating.java
    │   ├── LaughPower.java
    │   ├── SoulResonance.java
    │   └── Transmittable.java
    │
    └── types/
        ├── BattleLaugh.java
        ├── ForcedSmileLaugh.java
        ├── InheritedLaugh.java
        └── Laugh.java
```

**Package Organization**

| Package | Purpose |
|---|---|
| `laughter.app` | Main application entry point |
| `laughter.characters` | Specialized character classes |
| `laughter.interfaces` | Interfaces defining character abilities |
| `laughter.types` | Abstract base class and other main laugh types |


## How to Run

1. Clone or download the repository.
2. Open the project in **IntelliJ IDEA**.
3. Make sure a Java JDK is configured.
4. Locate `Main.java` inside `laughter.app`.
5. Run the `main()` method.

The program demonstrates the character hierarchy, their abilities, object interactions, keyboard input, and file output.

## Learning Objectives

The main goal of this project was to understand how to build a structured Java application using Object-Oriented Programming.

Instead of creating unrelated examples for each OOP concept, the project combines them into one model where classes, interfaces, inheritance, and polymorphism work together.

The architecture also allows new character types and abilities to be added without rewriting the existing hierarchy.

---

**Author:** Rosior Iulia  
**University:** Moldova State University (USM)   
**Course:** Advanced Programming Technologies (Java)  
**Year:** 2026

*An educational project inspired by One Piece. Original characters and related intellectual property belong to their respective copyright holders.*

The program demonstrates the character hierarchy, their abilities, object interactions, keyboard input, and file output.


