package ueb03;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class SetImpl<T extends Comparable> implements Set<T> {

	class Element {
		T val;
		Element left, right;
		Element(T v, Element l, Element r) {
			val = v;
			left = l;
			right = r;
		}
		int size() {
			return 1
					+ (left == null ? 0 : left.size())
					+ (right == null ? 0 : right.size());
		}
		public String toString() {
			return val
					+ (left == null ? "" : ", " + left)
					+ (right == null ? "" : ", " + right);
		}
	}

	Element root;

	@Override
	public boolean add(T s) {
		return addElement(new Element(s, null, null));
	}

	private boolean addElement(Element e) {
		if (e == null)
			return false;

		if (root == null) {
			root = e;
			return true;
		}

		Element it = root;
		while (it != null) {
			int c = e.val.compareTo(it.val);
			if (c == 0)
				return false;
			else if (c < 0) {
				if (it.left == null) {
					it.left = e;
					return true;
				} else
					it = it.left;
			} else {
				if (it.right == null) {
					it.right = e;
					return true;
				} else
					it = it.right;
			}
		}

		return false;
	}

	@Override
	public boolean contains(T s) {
		if (root == null)
			return false;

		Element it = root;
		while (it != null) {
			int c = s.compareTo(it.val);
			if (c == 0)
				return true;
			else if (c < 0) {
				it = it.left;
			} else {
				it = it.right;
			}
		}

		// nicht gefunden!
		return false;
	}

	@Override
	public T remove(T s) {
		if (root == null)
			throw new NoSuchElementException();

		// Spezialfall: Root Element loeschen
		if (root.val.equals(s))
			return removeRoot();

		Element it = root;
		while (it != null) {
			if (s.compareTo(it.val) < 0) {
				if (it.left != null && it.left.val.equals(s))
					return removeElement(it, it.left);
				it = it.left;
			} else {
				if (it.right != null && it.right.val.equals(s))
					return removeElement(it, it.right);
				it = it.right;
			}
		}

		throw new NoSuchElementException();
	}

	private T removeRoot() {
		assert(root != null);

		Element e = root;
		if (e.left == null && root.right == null) {
			// keine Kinder -> Baum leer
			root = null;
		} else if (e.left == null) {
			// nur ein rechtes Kind -> das ist der neue Baum
			root = e.right;
		} else if (e.right == null) {
			// dito, fuer Links
			root = e.left;
		} else {
			// eines wird root, anderes einfuegen
			root = e.left;
			addElement(e.right);
		}

		// Wert zurueck geben
		return e.val;
	}

	/**
	 *
	 * @param p Elternelement
	 * @param e zu loeschendes Element
	 */
	private T removeElement(Element p, Element e) {
		if (e == p.left) {
			p.left = null;  // links abgestiegen
		} else {
			p.right = null;  // sonst: rechts
		}

		// Kinder einfuegen
		addElement(e.left);
		addElement(e.right);

		return e.val;
	}


	@Override
	public int size() {
		if (root == null) {
			return 0;
		} else {
			return root.size();
		}
	}

	@Override
	public String toString() {
		if (root == null) {
			return "[]";
		} else {
			return "[" + root.toString() + "]";
		}
	}
}
