#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *readLine(FILE *fp);

char *readLine(FILE *fp)
{
    char *line;
    char *chp;
    int len;
    line = (char *)malloc(256);
    fgets(line, 256, fp);
    if (feof(fp))
    {
        chp = NULL;
        return chp;
    }
    else
    {
        len = strlen(line);
        if (line[len - 1] == '\n')
        {
            line[len - 1] = '\0';
        }
        chp = line;
        return chp;
    }
}

int main()
{
    char *line;
    int i;
    char *moji;
    int mojilen;

    printf("Input line\n");

    line = readLine(stdin);
    if (ferror(stdin))
    {
        printf("IO Exception!\n");
        exit(1);
    }

    printf("Input line=%s\n", line);

    moji = (char *)malloc(sizeof("There was a young lady named Bright. Whose speed was much faster than light; she set out, one day in a relativeway, and returned home the previous night. --- Arthur Buller") + 1);
    strcpy(moji, "There was a young lady named Bright. Whose speed was much faster than light; she set out, one day in a relativeway, and returned home the previous night. --- Arthur Buller");
    mojilen = strlen(moji);

    // ここを作成
    for (i = 0; i < mojilen - 1; i++)
    {
        if (moji[i] == line[0])
        {
            if (moji[i + 1] == line[1])
            {
                printf("found at:%d\n", i);
                // ここを作成
            }
        }
    }

    free(moji);
    free(line);

    return 0;
}