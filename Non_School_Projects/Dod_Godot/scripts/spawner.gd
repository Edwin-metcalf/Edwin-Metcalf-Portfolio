extends Area2D


@onready var current_level = 0
#@onready var enemy_dict this would be for a dictionary for when to change levels
#wonder if i could do it with time

@export var max_spawn_distance = 220
@export var min_spawn_distance = 200
@export var debug_mode := false
@onready var ghost = preload("res://scenes/enemy.tscn")
@onready var rand = RandomNumberGenerator.new()
@onready var enemies_defeated = 0
@onready var timer = $Timer
@onready var spawn_area = $"spawn area"


func _ready():
	call_deferred("spawn_enemies")
	print("an enemy should be spawing")

func _get_valid_spawn() -> Vector2:
	
	var angle = rand.randf_range(0,2 * PI)
	var random_radius = rand.randf_range(min_spawn_distance, max_spawn_distance)
	
	var x = random_radius * cos(angle)
	var y = random_radius * sin(angle)
	
	var random_location = global_position + Vector2(x,y)
	
	return random_location

func is_colliding_with_walls(enemy):
	#if debug_mode:
		#RenderingServer.draw_circle(enemy.global_position, 10.0, Color.RED)
	var enemy_collision_shape = enemy.get_node("CollisionShape2D").shape
	var enemy_collision_location = enemy.global_transform
	
	var space_state = get_world_2d().direct_space_state
	
	var query = PhysicsShapeQueryParameters2D.new()
	query.shape = enemy_collision_shape
	query.transform = enemy_collision_location
	query.collide_with_bodies = true
	query.collide_with_areas = true
	
	query.exclude = [enemy]
	
	var result = space_state.intersect_shape(query)
	
	for collision in result:
		var collider = collision.collider
		print("Colliding with: ", collider.name)
		
		if collider.name == "Area2D" or collider.name == "spawn area" or collider.name == "Hitbox_component":
			break
			
		if collider.name != "Area2D" or collider.name != "spawn_area" or collider.name != "Hitbox_component":
			return true 
	return false
	
	
func spawn_enemies():
	var location_found = false
	var retry_count = 0
	const MAX_TRIES = 10
	print("spawn enemies has started")
	while not location_found and retry_count < MAX_TRIES:
		var random_position = _get_valid_spawn()
		var g = ghost.instantiate()
		g.global_position = random_position
		get_parent().get_parent().add_child(g)
		
		if is_colliding_with_walls(g):
			g.queue_free()
			retry_count += 1
			print("enemy spawned in wall try again")
		else:
			location_found = true
			print("enemy spawned!")
			return
	if not location_found:
		print("no valid location found")
	


func _on_timer_timeout():
	current_level += 1
	for i in range(current_level):
		spawn_enemies()
		await get_tree().create_timer(2.0).timeout
		
