# van-Emde-Boas
This is my work-in-progress implementation of van Emde Boas Trees. Note that arrays in this program are supposed to be bit arrays.

van Emde Boas trees are a non-binary tree in which all operations are O(log log u) time where u is the universe.
It works by grouping the universe into √u clusters of √u size. There is also a corresponding summary vector (a bit array of √u size) that indicates whether the corresponsing cluster is empty. 

To learn more about van Emde Boas trees check out my inforgraphic at this link:
https://www.canva.com/design/DAE7713L2qc/aNSvY82osO2CH_3-4ONKeA/view?utm_content=DAE7713L2qc&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton
