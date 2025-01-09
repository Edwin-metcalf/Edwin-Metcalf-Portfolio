import pygame
from math import atan2, cos, sin

class Player(pygame.sprite.Sprite):
	def __init__(self,pos):
		super().__init__()
		self.image = pygame.image.load("characters/playerbase64xV1.png").convert_alpha()
		self.rect = self.image.get_rect(topleft = pos)

		#player movement
		self.direction = pygame.math.Vector2(0,0)
		self.speed = 8
		self.gravity = 0.9
		self.jump_speed = -15

	def player_input(self):
		keys = pygame.key.get_pressed()
		if keys[pygame.K_d]:
			self.direction.x = 1

		elif keys[pygame.K_a]:
			self.direction.x = -1


		else:
			self.direction.x = 0
		if keys[pygame.K_SPACE]:
			self.jump()

'''	def attack_icon(self):
		mouse_pos = pygame.mouse.get_pos()
		angle = atan2(mouse_pos[0] - self.rect.centerx, mouse_pos[1] - self.rect.centery)

		triangle_size = 20
		vertices = [(self.rect.centerx + triangle_size * cos(angle),
                 self.rect.centery + triangle_size * sin(angle)),
                (self.rect.centerx + triangle_size * cos(angle + 0.5),
                 self.rect.centery + triangle_size * sin(angle + 0.5)),
                (self.rect.centerx + triangle_size * cos(angle - 0.5),
                 self.rect.centery + triangle_size * sin(angle - 0.5))]
	

		pygame.draw.polygon(screen,(255,255,255), vertices)'''



	def apply_gravity(self):
		self.direction.y += self.gravity
		self.rect.y += self.direction.y

	def jump(self):
		self.direction.y = self.jump_speed




	def update(self):
		self.player_input()
