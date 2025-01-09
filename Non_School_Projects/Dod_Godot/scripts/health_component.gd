extends Node2D
class_name HealthComponent

@export var MAX_HEALTH = 5.0
@export var KNOCKBACK_POWER = 1000
@export var KNOCKBACK_FRICTION = .5

@onready var health_bar = $"Health Bar"
@onready var entity = get_parent()

var knockback_velocity : Vector2 = Vector2.ZERO
var entity_velocity : Vector2
var health :float

func _ready():
	health = MAX_HEALTH
	health_bar.max_value = MAX_HEALTH
	health_bar.value = health
	health_bar.modulate = Color(0,1,0)
	print(health)
	
func _physics_process(_delta):
	if knockback_velocity.length() > 0:
		knockback_velocity *= KNOCKBACK_FRICTION
		entity_velocity = knockback_velocity
		entity.move_and_slide()
		
	if knockback_velocity.length() < 1:
		knockback_velocity = Vector2.ZERO
		
		

func damage(attack, player_velocity):
	health -= attack
	health_bar.value = health
	entity_velocity = entity.velocity
	print(entity)
	print(entity_velocity)
	knockback(entity.velocity, player_velocity)
	print(health)
	if health <= 0:
		get_parent().queue_free()
		if "kill_value" in get_parent():
			Global.score += get_parent().kill_value
		get_parent().queue_free()
	if health > MAX_HEALTH * 0.6:
		health_bar.modulate = Color(0,1,0)
	elif health > MAX_HEALTH * .3:
		health_bar.modulate = Color(1,1,0)
	else:
		health_bar.modulate = Color(1,0,0)

	
func knockback(entity_velocity_plug: Vector2, player_velocity):
	var knockback_direction = (player_velocity - entity_velocity_plug).normalized() * KNOCKBACK_POWER

	knockback_velocity = knockback_direction
	entity.set_physics_process(false)
	entity.velocity = knockback_direction
	
	var timer = get_tree().create_timer(.5)
	await timer.timeout
	entity.set_physics_process(true)

