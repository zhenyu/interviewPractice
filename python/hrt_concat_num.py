def count_concat_num(numbers, value):
    m = {}
    ret =0
    for n in numbers:
        count = m.get(str(n), 0) +1
        m[str(n)] = count
    v_str = str(value)
    for n in numbers:
        ns = str(n)
        if v_str.startswith(ns):
            ret+=m.get(v_str[len(ns):],0)
    return ret

if __name__ == '__main__':
    print(count_concat_num([12, 1212, 121,120,0], 12120))