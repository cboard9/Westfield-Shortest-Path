

public class MinHeap
{
    int capacity;
    int size = 0;
    Node[] arr;

    public MinHeap(int capacity)
    {
        this.capacity = capacity;
        arr = new Node[capacity];
    }

    public MinHeap(Node[] arr)
    {
        this.arr = arr;
        this.capacity = arr.length;
        size = arr.length-1;
    }

    public int getLeftChildIndex(int parentIndex)
    {
        return (2 * parentIndex + 1);
    }

    public int getRightChildIndex(int parentIndex)
    {
        return (2 * parentIndex + 2);
    }

    public int getParentIndex(int childIndex)
    {
        return (childIndex - 1) / 2;
    }
    public void swap(int parentIndex, int childIndex)
    {
        Node parent = arr[parentIndex];
        Node child = arr[childIndex];
        arr[childIndex] = parent;
        arr[parentIndex] = child;
    }

    // heapify's up from index
    public void heapify(int index)
    {
        int parentIndex = getParentIndex(index);
        while(parentIndex >= 0)
        {
            int leftIndex = getLeftChildIndex(parentIndex);
            int rightIndex = getRightChildIndex(parentIndex);

            double parentDistance = arr[parentIndex].distance;

            double leftDistance = Double.MAX_VALUE;
            if(arr[leftIndex] != null)
                leftDistance = arr[leftIndex].distance;

            double rightDistance = Double.MAX_VALUE;
            if(arr[rightIndex] != null)
                rightDistance = arr[rightIndex].distance;

            if(leftDistance <= rightDistance && leftDistance < parentDistance)
            {
                swap(parentIndex, leftIndex);
                parentIndex = getParentIndex(parentIndex);
            }
            else if(rightDistance < parentDistance)
            {
                swap(parentIndex, rightIndex);
                parentIndex = getParentIndex(parentIndex);
            }
            else
                break;
        }
    }

    public void heapifyDown(int parentIndex)
    {
        while(parentIndex < size-2)
        {
            int leftIndex = getLeftChildIndex(parentIndex);
            int rightIndex = getRightChildIndex(parentIndex);

            double parentDistance = arr[parentIndex].distance;

            double leftDistance = Double.MAX_VALUE;
            if(arr[leftIndex] != null)
                leftDistance = arr[leftIndex].distance;

            double rightDistance = Double.MAX_VALUE;
            if(arr[rightIndex] != null)
                rightDistance = arr[rightIndex].distance;

            if(leftDistance <= rightDistance && leftDistance < parentDistance)
            {
                swap(parentIndex, leftIndex);
                parentIndex = leftIndex;
            }
            else if(rightDistance < parentDistance)
            {
                swap(parentIndex, rightIndex);
                parentIndex = rightIndex;
            }
            else
                break;
        }
    }


    public void insert(Node node)
    {
        double value = node.distance;
        if(size < capacity)
        {
            arr[size] = node;
            size++;
            heapify(size - 1);
        }
    }

    //deletes from root
    public Node delete()
    {
        Node first = arr[0];
        Node last = arr[size - 1];
        arr[0] = last;
        arr[--size] = null;

        heapifyDown(0);

        return first;
    }


    public void print()
    {
        for(int i=0; i<size; i++)
        {
            System.out.print(arr[i].distance + " ");
        }
        System.out.println();
    }

}
