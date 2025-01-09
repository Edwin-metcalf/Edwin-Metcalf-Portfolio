	
class 
	def attack_icon(self):
		mouse_pos = pygame.mouse.get_pos()
		angle = atan2(mouse_pos[0] - self.rect.centerx, mouse_pos[1] - self.rect.centery)

		triangle_size = 20
		vertices = [(self.rect.centerx + triangle_size * cos(angle),
                 self.rect.centery + triangle_size * sin(angle)),
                (self.rect.centerx + triangle_size * cos(angle + 0.5),
                 self.rect.centery + triangle_size * sin(angle + 0.5)),
                (self.rect.centerx + triangle_size * cos(angle - 0.5),
                 self.rect.centery + triangle_size * sin(angle - 0.5))]
	

		pygame.draw.polygon(screen,(255,255,255), vertices)
