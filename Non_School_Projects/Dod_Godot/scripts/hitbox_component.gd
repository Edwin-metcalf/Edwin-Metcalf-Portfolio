extends Area2D
class_name HitboxComponent

@export var health_component : HealthComponent

func damage(attack,player_velocity):
	if health_component:
		health_component.damage(attack, player_velocity)
