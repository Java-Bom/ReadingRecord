package chapter1.item4

interface Car

class Flat126P: Car

val DEFAULT_CAR: Car = Flat126P()

interface CarFactory {
    fun produce() = DEFAULT_CAR
}