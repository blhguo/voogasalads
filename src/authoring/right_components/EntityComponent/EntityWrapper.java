package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import authoring.component_menus.ComponentMenu;
import authoring.component_menus.ComponentMenuFactory;
import authoring.component_menus.MenuElement;
import frontend_utilities.DraggableImageView;
import frontend_utilities.ImageBuilder;
import game_engine.Component;
import game_engine.Entity;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import resources.keys.AuthRes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EntityWrapper {

	public Entity getEntity() {
		//System.out.println("ENTITY: " + entity);
		return entity;
	}

	private Entity entity;
	private ImageView imageView;
	private ImageView dummyImageView;
	private List<ComponentMenu> menuList;
	private EntityPane entityPane;
	private final DropShadow ds = new DropShadow(20, Color.DARKMAGENTA);
	private int levelId = 0;

	public EntityWrapper(EntityPane pane) {
		entity = new Entity();
		menuList = new ComponentMenuFactory().getDefaultMenus();
		menuList.stream().forEach(d -> d.setMyPane(pane));
		for (ComponentMenu menu : menuList) {
			for (MenuElement element : menu.getElements()) {
				element.setMyWrapper(this);
			}
		}
		//menuList.stream().forEach(c -> c.setMyPane(pane));
		addAllComponents(entity);
		imageView = createImageView();
		dummyImageView = new ImageView(new Image(entity.getComponent(FilenameComponent.class).getValue()));
		entityPane = pane;
	}

	public EntityWrapper(EntityWrapper e, EntityPane pane) {
		entity = new Entity();
		menuList = copyMenuList(e.getMenuList());
		addAllComponents(entity);
		imageView = createImageView();
		dummyImageView = new ImageView(new Image(entity.getComponent(FilenameComponent.class).getValue()));
		entityPane = pane;

	}

	public EntityWrapper(Entity e, EntityPane pane) {
		ResourceBundle componentBundle = ResourceBundle.getBundle("Component");
		entity = e;
		entityPane = pane;
		menuList = new ComponentMenuFactory().getDefaultMenus();
		for (ComponentMenu menu : menuList) {
			for (MenuElement element : menu.getElements()) {
				for (Component c : e.getComponents()) {
					if (element.getComponent().getClass() == c.getClass()) {
						element.setMyComponent(c);
						if (c.getValue() != null)
							element.setValue(c.getValue());
						entity.addComponent(c);
						menu.Include();
					}
				}
			}

		}
		imageView = createImageView();
		dummyImageView = new ImageView(new Image(entity.getComponent(FilenameComponent.class).getValue()));
	}

	private List<ComponentMenu> copyMenuList(List<ComponentMenu> menus) {
		List<ComponentMenu> newList = new ArrayList<>();
		for (ComponentMenu menu : menus) {
			newList.add(menu.copy());
		}
		return newList;
	}


	@SuppressWarnings("unchecked")
	public void addAllComponents(Entity entity) {
		for (ComponentMenu menu : menuList) {
			for (MenuElement element : menu.getElements()) {
				if (menu.isIncluded()) {
					entity.addComponent(element.getComponent());
				}
				else {
						entity.removeComponent((Class<? extends Component<?>>)
								element.getComponent().getClass());

				}
			}
		}
	}


		private ImageView createImageView () {
			DraggableImageView iv = ImageBuilder.getDraggableImageView(
					entity.getComponent(FilenameComponent.class).getValue(),
					entity.getComponent(WidthComponent.class).getValue().intValue(),
					entity.getComponent(HeightComponent.class).getValue().intValue());
			//TODO Set on mouse clicked to update the current EntityWrapper
			iv.setOnMouse(e -> {
				onClicked();
				e.consume();
			});
			iv.setX(entity.getComponent(XPosComponent.class).getValue());
			iv.setY(entity.getComponent(YPosComponent.class).getValue());
			iv.setOnMouseReleased(e -> setPos(iv.getX(), iv.getY()));
			if (!entity.getComponent(VisibilityComponent.class).getValue()) {
				iv.setOpacity(.2);
			}
			return iv;
		}

		private void onClicked () {
			entityPane.clearImageViews();
			entityPane.setActiveWrapper(this);
			entityPane.getPureCurrent().getImageView().setEffect(ds);
		}

		public void setPos ( double x, double y){
			entity.getComponent(XPosComponent.class).setValue(x);
			entity.getComponent(YPosComponent.class).setValue(y);
			imageView.setX(entity.getComponent(XPosComponent.class).getValue());
			imageView.setY(entity.getComponent(YPosComponent.class).getValue());
			for (ComponentMenu menu : menuList) {
				for (MenuElement element : menu.getElements()) {
					if (element.getComponent() instanceof XPosComponent) {
						element.setValue(x);
					} else if (element.getComponent() instanceof YPosComponent) {
						element.setValue(y);
					}
				}
			}
		}
		public Node getView () {
			Accordion acc = new Accordion();
			List<ComponentMenu> list = menuList.stream().filter(e -> e.isIncluded()).collect(Collectors.toList());
			acc.getPanes().addAll(
					list.stream().map(e -> e.getTitledPane())
							.collect(Collectors.toList())
			);
			ScrollPane pane = new ScrollPane(acc);
			pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
			pane.setStyle("-fx-background: transparent;");
			pane.setPrefHeight(AuthRes.getInt("PrefWrapperHeight"));
			pane.setPrefWidth(AuthRes.getInt("PrefWrapperWidth"));
			return pane;
		}
		public void updateImage () {
			imageView.setX(entity.getComponent(XPosComponent.class).getValue());
			imageView.setY(entity.getComponent(YPosComponent.class).getValue());
			imageView.setImage(
					new Image(entity.getComponent(FilenameComponent.class).getValue()));
			imageView.setFitWidth(entity.getComponent(WidthComponent.class).getValue());
			imageView.setFitHeight(entity.getComponent(HeightComponent.class).getValue());
			if (!entity.getComponent(VisibilityComponent.class).getValue()) {
				imageView.setOpacity(.2);
			} else {
				imageView.setOpacity(1);
			}
		}

		public void updateSprite () {
			entityPane.updateSprite();
		}

		public ImageView getImageView () {
			return imageView;
		}

		public List<ComponentMenu> getMenuList () {
			return menuList;
		}

		public void setImageViewStyle (String s){
			imageView.setStyle("");
		}
		public ImageView getDummy () {
			dummyImageView.setX(imageView.getX());
			dummyImageView.setY(imageView.getY());
			dummyImageView.setFitHeight(imageView.getFitHeight());
			dummyImageView.setFitWidth(imageView.getFitWidth());
			dummyImageView.setImage(imageView.getImage());
			return dummyImageView;
		}

		public void setLevel ( int id){
			levelId = id;
		}

		public int getLevel () {
			return levelId;
		}
}

