package project.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

public class CellBrowserInverter {

	/**
	 * A list of brands.
	 */
	private static class listInv {
		private final String brand;
		private final List<String> brandInv = new ArrayList<String>();

		public listInv(String brand) {
			this.brand = brand;
		}

		/**
		 * Add a brand to the listInv.
		 * 
		 * @param brand
		 *            the brand of the brand
		 */
		public void addBrandInv(String brand) {
			brandInv.add(brand);
		}

		public String getBrandInv() {
			return brand;
		}

		/**
		 * Return the list of brands in the listInv.
		 */
		public List<String> getBrandInvList() {
			return brandInv;
		}
	}

	// End List brands

	/**
	 * A brand of Inverter.
	 */
	private static class Brand {
		private final String brand;
		private final List<listInv> invLists = new ArrayList<listInv>();

		public Brand(String brand) {
			this.brand = brand;
		}

		/**
		 * Add a invLists to the composer.
		 * 
		 * @param invLists
		 *            the invLists to add
		 */
		public listInv addinvList(listInv invList) {
			invLists.add(invList);
			return invList;
		}

		public String getbrand() {
			return brand;
		}

		/**
		 * Return the rockin' invLists for this composer.
		 */
		public List<listInv> getinvLlists() {
			return invLists;
		}
	}

	// End Brand

	/**
	 * The model that defines the nodes in the tree.
	 */
	public static class CustomTreeModel implements TreeViewModel {

		private final List<Brand> inverter;

		/**
		 * This selection model is shared across all leaf nodes. A selection
		 * model can also be shared across all nodes in the tree, or each set of
		 * child nodes can have its own instance. This gives you flexibility to
		 * determine how nodes are selected.
		 */
		private final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();

		public CustomTreeModel(ArrayList<String> inverterList) {
			// Create a database of information.
			inverter = new ArrayList<Brand>();

			
			// use a loop to go through the return of [getAllInverterBrands()]
			//for (int i = 0; i<arraylist.size(); i++) {
				
			// Add compositions by Conergy.
			{
				Brand nameBrand = new Brand("Select Inverter");
				inverter.add(nameBrand);
				for (int i = 0; i<inverterList.size(); i++) {
					String line = inverterList.get(i).toString();
					
					String delims = "[,]";
					String[] tokens = line.split(delims);
					
					
					
					
					
					listInv InverterBrand = nameBrand.addinvList(new listInv(tokens[0].toString()));
					for ( int i2 = 1; i2 < tokens.length; i2++){
						InverterBrand.addBrandInv(tokens[i2].toString());
					}
					
					
				}
				/*
				 * Composer [BrandName] = new Composer([BrandName].toString());
				 * inverter.add([BrandName]);
				 * 
				 * Inverter [BrandName] = [BrandName].addInverter(new Inverter("Concertos")); 
				 * //use a loop to go through the return of [getInverterSerialNumberByBrand([BrandName])]
				 * concertos.addSong("No. 1 - C");
				 */
				
			}

		}

		/**
		 * Get the {@link NodeInfo} that provides the children of the specified
		 * value.
		 */
		public <T> NodeInfo<?> getNodeInfo(T value) {
			if (value == null) {
				// LEVEL 0.
				// We passed null as the root value. Return the inverter.

				// Create a data provider that contains the list of inverter.
				ListDataProvider<Brand> dataProvider = new ListDataProvider<CellBrowserInverter.Brand>(
						inverter);

				// Create a cell to display a Brand.
				Cell<Brand> cell = new AbstractCell<Brand>() {
					@Override
					public void render(
							com.google.gwt.cell.client.Cell.Context context,
							Brand value, SafeHtmlBuilder sb) {
						if (value != null) {
							sb.appendEscaped(value.getbrand());
						}
					}
				};

				// Return a node info that pairs the data provider and the cell.
				return new DefaultNodeInfo<Brand>(dataProvider, cell);
			} else if (value instanceof Brand) {
				// LEVEL 1.
				// We want the children of the Brand. Return the Inverters.
				ListDataProvider<listInv> dataProvider = new ListDataProvider<listInv>(
						((Brand) value).getinvLlists());
				Cell<listInv> cell = new AbstractCell<listInv>() {
					@Override
					public void render(Context context, listInv value,
							SafeHtmlBuilder sb) {
						if (value != null) {
							sb.appendEscaped(value.getBrandInv());
						}
					}
				};
				return new DefaultNodeInfo<listInv>(dataProvider, cell);
			} else if (value instanceof listInv) {
				// LEVEL 2 - LEAF.
				// We want the children of the Inverter. Return the songs.
				ListDataProvider<String> dataProvider = new ListDataProvider<String>(
						((listInv) value).getBrandInvList());

				// Use the shared selection model.
				return new DefaultNodeInfo<String>(dataProvider,
						new TextCell(), selectionModel, null);
			}

			return null;
		}

		/**
		 * Check if the specified value represents a leaf node. Leaf nodes
		 * cannot be opened.
		 */
		public boolean isLeaf(Object value) {
			// The leaf nodes are the songs, which are Strings.
			if (value instanceof String) {
				return true;
			}
			return false;
		}

	}

}
