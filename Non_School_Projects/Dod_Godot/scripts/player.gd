extends CharacterBody2D


@export var SPEED : float = 200.0 
@export var JUMP_VELOCITY : float = -300.0
@export var SWORD_DAMAGE = 1

var facing_right = true

var attack_offset_right= Vector2(0,0)
var attack_offset_left = Vector2(-30,0)

var gravity = ProjectSettings.get_setting("physics/2d/default_gravity")
@onready var animated_sprite = $AnimatedSprite2D
#@onready var glow = $WorldEnvironment
@onready var sword_area = $Sword
@onready var sword_hitbox = $Sword/CollisionShape2D

var current_state = player_states.MOVE
enum player_states {MOVE, SWORD}

var is_attacking = false


func _ready():
	animated_sprite.connect("animation_finished", Callable(self, "_on_animation_finished"))
	animated_sprite.offset = attack_offset_right

func _physics_process(delta):
	match current_state:
		player_states.MOVE:
			movement(delta)
		player_states.SWORD:
			sword(delta)

func movement(delta):
	#glow.environment.glow_intensity = 3
	# Add the gravity.
	if not is_on_floor():
		velocity.y += gravity * delta

	# Handle jump.
	if Input.is_action_just_pressed("jump"): #and is_on_floor():
		velocity.y = JUMP_VELOCITY
		
	var direction = Input.get_axis("move_left", "move_right")
	
	if direction > 0:
		animated_sprite.flip_h = false
		facing_right = true
		sword_area.position.x = 0

	elif direction < 0:
		animated_sprite.flip_h = true
		facing_right = false
		sword_area.position.x = -24
	
	if is_on_floor():
		if direction == 0:
			animated_sprite.play("Idle")
		else:
			animated_sprite.play("walk")
	elif velocity.y < 0:
		animated_sprite.play("jump")
	else:
		animated_sprite.play("fall")
	
	if direction:
		velocity.x = direction * SPEED
	else:
		velocity.x = move_toward(velocity.x, 0, SPEED)
		
	if Input.is_action_just_pressed("attack") and not is_attacking:
		is_attacking = true
		current_state = player_states.SWORD 
		
	move_and_slide()

	
func sword(delta):
	sword_hitbox.disabled = false
	#glow.environment.glow_intensity = .5
	if facing_right:
		animated_sprite.offset = attack_offset_right
	else:
		animated_sprite.offset = attack_offset_left
	if not is_on_floor() and Input.is_action_pressed("down"):
		animated_sprite.play("AttackDown")
		animated_sprite.offset = Vector2(-20,-30)
		if facing_right:
			sword_hitbox.position = Vector2(17,10)
		else:
			sword_hitbox.position = Vector2(40,10)
	elif Input.is_action_pressed("up"):
		animated_sprite.offset = Vector2(-17,-24)
		animated_sprite.play("AttackUp")
		if facing_right:
			sword_hitbox.position = Vector2(17,-10)
		else:
			sword_hitbox.position = Vector2(40,-10)
	else:
		animated_sprite.play("Attack1")
		
		
	input_movement(delta)

func input_movement(delta):
	if not is_on_floor():
		velocity.y += gravity * delta

	# Handle jump.
	if Input.is_action_just_pressed("jump"): #and is_on_floor():
		velocity.y = JUMP_VELOCITY
		
	var direction = Input.get_axis("move_left", "move_right")
	
	if direction > 0:
		animated_sprite.flip_h = false
		facing_right = true
		sword_area.position.x = 0
		
	elif direction < 0:
		animated_sprite.flip_h = true
		facing_right = false
		sword_area.position.x = -24
		
	
	if direction:
		velocity.x = direction * SPEED
	else:
		velocity.x = move_toward(velocity.x, 0, SPEED)
		
	move_and_slide()
	
func _on_animation_finished():
	if animated_sprite.animation == "Attack1":
		if not facing_right:
			animated_sprite.offset = Vector2(0,0)
			print("sentback")
		sword_hitbox.disabled = true
		
		reset_state()
		
	elif animated_sprite.animation == "AttackDown":
		animated_sprite.offset = Vector2(0,0)
		sword_hitbox.disabled = true
		reset_state()
		
	elif animated_sprite.animation == "AttackUp":
		animated_sprite.offset = Vector2(0,0)
		reset_state()

func reset_state():
	current_state = player_states.MOVE
	sword_hitbox.position = Vector2(30,2)
	
	is_attacking = false



func _on_sword_area_entered(area):
	#this funciton is connected to the sword collision box
	if area is HitboxComponent:
		#var hitbox : HitboxComponent = area
		area.damage(SWORD_DAMAGE,velocity)
		print("the player is attacking!")
