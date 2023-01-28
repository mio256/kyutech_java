#include <stdio.h>
#include <stdlib.h>

typedef struct dummy_LIST
{
    int data;
    struct dummy_LIST *next;
} LIST;

LIST *root;

LIST *newNode()
{
    LIST *p;
    p = (LIST *)malloc(sizeof(LIST));
    p->next = NULL;
    return p;
}

void init_dummy_node(void)
{
    // ここを作成せよ(実行行1行)
    root = newNode();
}

void add_to_1st_pos(int n)
{
    // ここを作成せよ
    LIST *p;
    p = root->next;
    root->next = newNode();
    root->next->data = n;
    root->next->next = p;
}

void print(void)
{
    LIST *p;
    for (p = root->next; p != NULL; p = p->next)
    {
        printf("%d ", p->data);
    }
    printf("\n");
}

LIST *search(int n)
{
    LIST *p;
    for (p = root->next; p != NULL; p = p->next)
    {
        if (p->data == n)
        {
            return p;
        }
    }
    return NULL;
}

void delete(LIST *dlt)
{
    LIST *p;
    for (p = root; p != NULL; p = p->next)
    {
        if (p->next == dlt)
        {
            p->next = p->next->next;
            break;
        }
    }
    free(dlt);
}

int main(void)
{
    int key;
    LIST *find;

    init_dummy_node();
    add_to_1st_pos(2);
    add_to_1st_pos(3);
    add_to_1st_pos(5);
    add_to_1st_pos(7);
    add_to_1st_pos(11);
    add_to_1st_pos(13);
    add_to_1st_pos(17);
    add_to_1st_pos(19);

    print();

    key = 13;
    printf("delete=%d\n", key);
    find = search(key);

    if (find != NULL)
    {
        delete (find);
    }
    else
    {
        printf("not found\n");
    }
    print();

    key = 19;
    printf("delete=%d\n", key);
    find = search(key);

    if (find != NULL)
    {
        delete (find);
    }
    else
    {
        printf("not found\n");
    }
    print();

    key = 3;
    printf("delete=%d\n", key);
    find = search(key);

    if (find != NULL)
    {
        delete (find);
    }
    else
    {
        printf("not found\n");
    }
    print();

    key = 2;
    printf("delete=%d\n", key);
    find = search(key);

    if (find != NULL)
    {
        delete (find);
    }
    else
    {
        printf("not found\n");
    }

    print();
    return 0;
}