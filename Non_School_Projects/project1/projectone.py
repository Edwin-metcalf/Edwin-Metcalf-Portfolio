import pygame
from sys import exit
from settings import *
from tiles import Tile
from level import Level


pygame.init()
screen = pygame.display.set_mode((screen_width,screen_height))
pygame.display.set_caption("Game1")
clock = pygame.time.Clock()
level = Level(level_map,screen)




game_active = True


while True:
	for event in pygame.event.get():
		if event.type == pygame.QUIT:
			pygame.quit()
			exit()

	if game_active:
		screen.fill("grey")
		level.run()

	else:
		print("gg")

	pygame.display.update()
	clock.tick(60)





