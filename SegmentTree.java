static class Node{
        int val;
        Node left, right;
        int start, end;
        Node(int start, int end){
            this.start = start;
            this.end = end;
            val = Integer.MAX_VALUE;
        }
        Node(int val, int start, int end){
            this.val = val;
            this.start = start;
            this.end = end;
        }
        void merge(Node left, Node right){
            val = Math.min(left.val, right.val);
        }
    }
    static class SGT{
        Node root;
        SGT(int n, int[] arr){
            root = buildSGT(0, n - 1, arr);
        }
        Node buildSGT(int start, int end, int[] arr){
            if(start > end){
                return null;
            }
            if(start == end){
                return new Node(arr[start], start, end);
            }
            int mid = (start + end) / 2;
            Node currNode = new Node(start, end);

            currNode.left = buildSGT(start, mid, arr);
            currNode.right = buildSGT(mid + 1, end, arr);

            currNode.merge(currNode.left, currNode.right);
            return currNode;
        }
        void updateSGT(int k, int x, int[] arr){
            update(root, k, x, arr);
        }
        void update(Node root, int k, int x, int[]  arr){
            if(root.start == root.end){
                root.val = x;
                return;
            }
            int mid = (root.start + root.end)/2;
            if(k <= mid){
                update(root.left, k, x, arr);
            }
            else{
                update(root.right, k, x, arr);
            }
            root.merge(root.left, root.right);
        }
        int rangeMin(int i, int j, int[] arr){
            return getRangeMin(root, i, j, arr);
        }
        int getRangeMin(Node root, int i, int j, int[] arr){
            if(root == null || root.end < i || root.start > j){
                return Integer.MAX_VALUE;
            }
            if(i <= root.start && j >= root.end){
                return root.val;
            }
            return Math.min(getRangeMin(root.left, i, j, arr) , getRangeMin(root.right, i, j, arr));
        }
    }
