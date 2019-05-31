
public class VendingMachine {
    private Item[] items;  // store items in a min-heap
    private int itemCount; // number of items in this heap
    // Note use of min-heap here, to prioritize the smallest (soonest) expiration day.
    // You may decide to use either 0 or 1 as the top-index in this items array.
    
    public VendingMachine (int capacity) {
        items = new Item[capacity];
        itemCount = 0;
    }
    
    private int getParent(int childIndex) {
        // return the parent index of the given child index 
        return (childIndex-1)/2;
    }
     
    private int getLeftChild(int parentIndex) {
        // return the left child index of the given parent index
        return parentIndex*2+1;
    }
     
    private int getRightChild(int parentIndex) {
        // return the right child index of the given parent index
        return parentIndex*2+2;
    }
     
    private void swap(int itemOneIndex, int itemTwoIndex) {
        // swaps the Item references at itemOneIndex and itemTwoIndex in the items array
        Item temp = items[itemOneIndex];
        items[itemOneIndex] = items[itemTwoIndex];
        items[itemTwoIndex] = temp;
    }
     
    private void addHelper(int index) {
        // Propagates the min-heap order property from the node at position index,
        // up through it's ancestor nodes. Assumes that only the node at position
        // index may be in violation of this property. This is useful when adding
        // a new item to the bottom of the heap.
        
        if(items[index].getExpirationDay() >= items[getParent(index)].getExpirationDay()) {
            return;
        }
        else {
            swap(index, getParent(index));
            addHelper(getParent(index));
        }
    }
     
    private void removeHelper(int index) {
        // Propagates the min-heap order property from the node at position index,
        // down through it's highest priority descendant nodes. Assumes that the
        // children of the node at position index conform to this heap property. 
        // This is useful when removing an item from the top of the heap.
        if (items[getLeftChild(index)] != null) {
            if (items[getRightChild(index)]==null) {
                swap(index,getLeftChild(index));
                removeHelper(getLeftChild(index));
            } else if (items[getLeftChild(index)].getExpirationDay() <= items[getRightChild(index)].getExpirationDay()) {
                swap(index,getLeftChild(index));
                removeHelper(getLeftChild(index));
            }
            
        } else if (items[getRightChild(index)] != null) {
            if (items[getLeftChild(index)]==null) {
                swap(index,getRightChild(index));
                removeHelper(getRightChild(index));
            } else if (items[getLeftChild(index)].getExpirationDay() >= items[getRightChild(index)].getExpirationDay()) {
                swap(index,getRightChild(index));
                removeHelper(getRightChild(index));
            }
        }
    }
    
    public void addItem(Item item) {
        // Add the given item to the items array and perform the necessary
        // actions to maintain the min-heap properties.
        if (items.length > itemCount) {
            items[itemCount] = item;
            addHelper(itemCount);
            itemCount++;
        } else {
            throw new IllegalStateException("WARNING: Item not added.  This vending machine is already filled to capacity.");
        }
        
    }
     
    public Item dispenseNextItem() {
        // Dispense the item with the smallest expiration date from this 
        // vending machine, while maintaining the min-heap properties.
        // This method removes the item returned from the heap.
        if (items[0]==null) {
            throw new IllegalStateException("WARNING: Operation not allowed.  This vending machine is empty.");
        }
        Item temp = items[0];
        items[0] = null;
        removeHelper(0);
        itemCount--;
        return temp;
    }
         
    public Item getNextItem() {
        // This method returns a reference to the next item that will be dispensed.
        // This method does NOT change the state of the Vending Machine or its heap.
        if (items[0]==null) {
            throw new IllegalStateException("WARNING: Operation not allowed.  This vending machine is empty.");
        }
        return items[0];
    }
     
     public Item dispenseItemAtIndex(int index) {
        // Dispense the item from a particular array index, while maintaining
        // the min-heap properties.  This method removes that item from the heap.
        // This index parameter assumes the top-index is zero.  So you'll need to
        // add one to this index, if you are using the top-index = 1 convention.
         if (items[0]==null) {
             throw new IllegalStateException("WARNING: Operation not allowed.  This vending machine is empty.");
         } else if (items[index]==null) {
             throw new IllegalStateException("WARNING: Operation not allowed.  Index is invalid.");
         }
         Item temp = items[index];
         items[index] = null;
         removeHelper(index);
         itemCount--;
         return temp;
    }
     
    public Item getItemAtIndex(int index) {
        // This method returns a reference to the item at position index.
        // This method does not change the state of the vending machine.
        // This index parameter assumes the top-index is zero. So you'll need to 
        // add one to this index, if you are using the top-index = 1 convention.     
        if (items[0]==null) {
            throw new IllegalStateException("WARNING: Operation not allowed.  This vending machine is empty.");
        } else if (items[index]==null) {
            throw new IllegalStateException("WARNING: Operation not allowed.  Index is invalid.");
        }
        return items[index];
    }
}
